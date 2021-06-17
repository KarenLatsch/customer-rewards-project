# customer-rewards-project

My customer rewards project is a Rest API application that uses Spring Web and Java. It takes in single transactions  
of a customer's purchase and returns reward points earned for each customer totaled within a specific month. 

This application is intended for the UI to handle the customer's totals for a three-month period. 
This allows the front-end to calulate all totals needed in the future without changing the back-end.  



The data recieved and sent back from the application is in JSON format.

# Format recieved into application:

[{
     "customerId": 1,
     "transDate": "2020-09-30",
     "transAmount": 120.34 
   }, 
   {
     "customerId": 1,
     "transDate": "2020-12-12",
     "transAmount": 10.34 
   },
   {
     "customerId": 1,
     "transDate": "2020-04-10",
     "transAmount": 100.34 
   },
   {
     "customerId": 2,
     "transDate": "2020-01-30",
     "transAmount": 59.34 
   }  

]

# Format sent from application:

[
    {
        "customerId": 1,
        "yearMonth": 202009,
        "points": 90
    },
    {
        "customerId": 2,
        "yearMonth": 202001,
        "points": 9
    },
    {
        "customerId": 1,
        "yearMonth": 202004,
        "points": 50
    }
] 

# Post mapping:

http://localhost:8080/points


# Reward points are base off of the following:

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

 
