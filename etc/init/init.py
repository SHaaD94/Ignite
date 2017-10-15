import json
import random
import requests

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


def main():
    global server_url
    print('Starting initialization')
    for cellId in range(1, 1000):
        post(server_url + '/cells', json.dumps({"id": cellId}))
        for _ in range(1, random.randint(2, 100)):
            post(server_url + '/profiles',
                 json.dumps(
                     {"cellId": cellId,
                      "name": "asdasd",
                      "email": "as@email.com",
                      "activationDate": 123123123}))

    print('Initialization finished successfuly')


if __name__ == '__main__':
    main()
