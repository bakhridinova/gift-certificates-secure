# Gift Certificates Secure API

this API allows you to do create, update, update and delete (aka CRUD) operations on entities depending on your role
it supports Basic and Bearer Authentication types

## GUEST:

| Entity      | C | R | U | D |
|:------------|:-:|:-:|:-:|:-:|
| Certificate | - | + | - | - |
| Order       | - | - | - | - |
| Tag         | - | + | - | - |
| Token       | - | - | - | - |
| User        | - | - | - | - |

## USER

| Entity      | C | R | U | D |
|:------------|:-:|:-:|:-:|:-:|
| Certificate | - | + | - | - |
| Order       | - | + | + | - |
| Tag         | - | + | - | - |
| Token       | + | + | - | - |
| User        | - | + | - | - |

## ADMIN

| Entity      | C | R | U | D |
|:------------|:-:|:-:|:-:|:-:|
| Certificate | + | + | + | + |
| Order       | + | + | + | - |
| Tag         | + | + | + | + |
| Token       | + | + | - | + |
| User        | + | + | - | - |

## Set up 
- [*clone*](https://github.com/bakhridinova/gift-certificates-secure.git) the project
- change [application.yml](web-service/src/main/resources/application.yml) file based on your database configurations
- run the project using [GiftCertificatesSecureApplication.java](web-service/src/main/java/com/epam/esm/GiftCertificatesSecureApplication.java) 

# API Reference 

## Get all entities

```agsl
request parameters:
    int page (defaultValue 1)
    int size (defaultValue 5)
```

**GET** `/api/certificates`
  returns list of certificates


**GET** `/api/orders`
  returns list of orders  


**GET** `/api/tags`
  returns list of tags  

**GET** `/api/tokens`
returns list of tokens

**GET** `/api/users`
  returns list of users

## Get entity by id

**GET** `/api/certificates/{id}`
  returns certificate by given id if one exists


**GET** `/api/orders/{id}`
  returns order by given id if one exists


**GET** `/api/tags/{id}`
  returns tag by given id if one exists

**GET** `/api/tokens/{id}`
returns token by given id if one exists

**GET** `/api/users/{id}`
  returns user by given id if one exists

## Registration and Authentication

```agsl
request body:
        {
            "username": "",
            "password": ""
        }
```

**POST** `/api/users/register`

```agsl
request body:
        {
            "username": "",
            "password": ""
        }
```

**POST** `/api/users/authenticate`

## Get user's orders

```agsl
request parameters:
    int page (defaultValue 1)
    int size (defaultValue 5)
    long userId 
```

**GET** `/api/orders/search`
returns orders of user by given id if one exists

## Get user's tokens

```agsl
request parameters:
    int page (defaultValue 1)
    int size (defaultValue 5)
    long userId 
```

**GET** `/api/orders/search`
returns tokens of user by given id if one exists

## Get special tag

**GET** `/api/tags/special`
returns the most commonly used tag of a user with the highest cost of all orders


## Get certificates by filter
returns list of certificates filtered by passed parameters and tags

```agsl
request parameters:
    int page (defaultValue 1) 
    int size (defaultValue 5) 
```

```agsl
request body:
        {
            "name": "",
            "description": "",
            "sortType": "",
            "sortOrder": "",
            "tags": [
                {
                    "name": ""
                }
            ]
        }
```

## Create entity

**POST** `/api/certificates`
returns created certificate

```agsl
request body:
        {
            "name": "",
            "description": "",
            "price": 0,
            "duration": 0,
            "tags": [
                {
                    "id": "",
                    "name": ""
                }
            ] 
        }
```

**POST** `/api/orders`
returns created order

```agsl
request body:
        {
            "userId": 0,
            "certificateId": 0
        }
```

**POST** `/api/tags`
returns created tag

```agsl
request body:
        {
            "name": ""
        }
```

## Update entity

**PATCH** `/api/certificates/{id}`
returns updated certificate

```agsl
request body:
        {
            "name": ""
        }
```

**PATCH** `/api/orders/{id}/pay`
returns updated order with PAID status

**PATCH** `/api/orders/{id}/cancel`
returns updated order CANCELLED status


## Delete entity
**DELETE** `/api/certificates/{id}`
deletes certificate by given id if one exists


**DELETE** `/api/tags/{id}`
deletes tag by given id if one exists

entity relationship diagram

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/100201504/239057032-5b031c7e-b679-44cc-9233-b21fe8d2857e.png" alt=""/>