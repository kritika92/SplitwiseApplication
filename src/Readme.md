# Add Users #
```

pk: id
Method: POST
endpoint: http://localhost:8080/add/user/
requestbody: {
   "id" :"1",
   "name": "kritika",
   "email":"kritika@gmail.com",
   "mobileNumber":"73847336284"
   }
responseBody :ListofAllUsers
   [User{id='1', name='kritika', email='kritika@gmail.com', mobileNumber='73847336284'}]

```
### Example:
```
POST /add/user/? HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: 0d622d1e-5a9f-4a60-9725-2dbc5e5f934f
{
"id" :"1",
"name": "kritika",
"email":"kritika@gmail.com",
"mobileNumber":"73847336284"
}------WebKitFormBoundary7MA4YWxkTrZu0gW--



POST /add/user/? HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: e8265aee-850b-4634-8030-8170bfd79037
{
"id" :"2",
"name": "shivangi",
"email":"shivangi@gmail.com",
"mobileNumber":"73847336284"
}------WebKitFormBoundary7MA4YWxkTrZu0gW-----WebKitFormBoundary7MA4YWxkTrZu0gW--
```

# Add expense:
```
Method: POST
endpoint: http://localhost:8080/add/expense/

expenseType: can be PERCENT, EXACT, EQUAL

amount: total amount paid by the paidByUserId 

paymentMapModel:  is a map of key as userId and value as following:
value is PERCENT borne by the userId in case expenseType is PERCENT
value is actual amount if expenseType is EXACT
value doesn't matter in case of expenseType is EQUAL

requestbody: {
"paidByUserId" :"2",
"amount": "1000",
"paymentMapModel":{"1": 0 , "2":0},
"nameExpense":"party",
"date":"24/12/2021",
"imgUrl":"s3url.com",
"expenseType":"EQUAL"
}
response:
Expense{id=1, paidByUserId='2', amount=1000.0, paymentMap='{"1":0.0,"2":0.0}', nameExpense='party', Date='24/12/2021', imgUrl='s3url.com', expenseType=EQUAL}
```
### Example
```
POST /add/expense/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: 9c1ef203-bd11-47fe-8d2f-55047eab1d31
{
"paidByUserId" :"2",
"amount": "1000",
"paymentMapModel":{"1": 0 , "2":0},
"nameExpense":"party",
"date":"24/12/2021",
"imgUrl":"s3url.com",
"expenseType":"EQUAL"
}

------WebKitFormBoundary7MA4YWxkTrZu0gW--



POST /add/expense/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: 76efdd3a-80dd-41b8-9836-9f8f1c3d2087
{
"paidByUserId" :"2",
"amount": "1000",
"paymentMapModel":{"1": 90, "2":10},
"nameExpense":"party",
"date":"24/12/2021",
"imgUrl":"s3url.com",
"expenseType":"PERCENT"
}

------WebKitFormBoundary7MA4YWxkTrZu0gW--
POST /add/expense/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: 61745709-1a7b-4be6-8451-4bdf25a42e63
{
"paidByUserId" :"1",
"amount": "1000",
"paymentMapModel":{"1": 800, "2":200},
"nameExpense":"party",
"date":"24/12/2021",
"imgUrl":"s3url.com",
"expenseType":"EXACT"
}

------WebKitFormBoundary7MA4YWxkTrZu0gW--
```

# Show balance
```
GET http://localhost:8080/show/balance/?userId=1
ResponseBody:
   [Amount userId 1has to give others is 1400.0, userId1 owes 1400.0 amount to userId 2]
```
Enter the userId in the request params to know the balance
and his respective balance with other users.
The return response body is a list whose first element
tells the total amount the userId owes or has to recieve 
The element after first element are the details of balances with
the other users.
In case the userId doesn't have to pay anyone and other
userIds also donot have to anything to  this userId then
the response will be "[Everything is settled]"





