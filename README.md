# Ignite

### Apache ignite first experience

### Run
```
gradle clean buildDocker
docker-compose -f etc/docker-compose/docker-compose.yml up
python3 etc/init/init.py
```
Service will be available at localhost:8080

### API
Get profiles by cell_id:
```
GET /cells/:id/profiles
```
Add cell_id:
```
POST /cells
{
   "id" : 123
}
```
Add profile:
```
POST /profiles
{
    "cellId": 123,
    "name": "Ivan Ivanov",
    "email": "i.ivanov@gmail.com",
    "activationDate": 1508074728
}
```
