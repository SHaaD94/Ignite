import json
import random
import requests
import string
import time
from threading import Thread

server_url = "http://127.0.0.1:8080"


def request(method, uri, data=None, headers=None):
    try:
        resp = requests.request(method=method, url=uri, data=data, headers=headers)
        resp.raise_for_status()
        if resp.status_code == 200:
            return resp
        else:
            print('Status is not 200!\n' + str(resp))
    except Exception as e:
        print('Exception! \n' + str(e))

    raise Exception("Failed to send HTTP request")


def post(url, data):
    headers = {'content-type': 'application/json'}
    return request(method='POST', uri=url, data=data, headers=headers).json()


def get_random_string():
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(10))


def generate_data(cell_ids):
    for cell_id in cell_ids:
        print('Generating data for cell_id ' + str(cell_id))
        post(server_url + '/cells', json.dumps({"id": cell_id}))
        for _ in range(1, random.randint(2, 100)):
            post(server_url + '/profiles',
                 json.dumps(
                     {"cellId": cell_id,
                      "name": get_random_string(),
                      "email": get_random_string() + "@gmail.com",
                      "activationDate": int(time.time())}))


def main():
    global server_url
    print('Starting initialization')

    cell_ids = range(1, 1000)
    chunk = lambda ulist, step: map(lambda i: ulist[i:i + step], range(0, len(ulist), step))
    cell_ids_chunked = chunk(cell_ids, 50)

    threads = []
    for chunk in cell_ids_chunked:
        t = Thread(target=generate_data, args=[chunk])
        t.start()
        threads.append(t)

    for t in threads:
        t.join()

    print('Initialization finished successfuly')


if __name__ == '__main__':
    main()
