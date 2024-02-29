# CryptoTP

# CriptoDealer API

- POST localhost:8099/api/cryptos?new=cryptoname
ajoute une crypto à partir de son nom,
générère un stock, une valeur aléatoirement
retour : la crypto complète / lève une exception si le nom est déjà dans la base de donnée

exemples:
localhost:8099/api/cryptos?new=cashCoin

{
    "id": 1,
    "name": "cashCoin",
    "availableStock": 59624.47018360035,
    "currentValue": 305.2993630043312,
    "logTime": 1709205100
}

localhost:8099/api/cryptos?new=beerCoin
{
    "id": 2,
    "name": "beerCoin",
    "availableStock": 45036.16155008237,
    "currentValue": 299.32745220949266,
    "logTime": 1709205102
}

localhost:8099/api/cryptos?new=utopiosCoin

{
    "id": 3,
    "name": "utopiosCoin",
    "availableStock": 76821.46202967674,
    "currentValue": 950.019897403823,
    "logTime": 1709205105
}


- GET localhost:8099/api/cryptos

- GET localhost:8099/api/cryptos/{cryptoName}

- GET localhost:8099/api/cryptos/{crypto}?minutes=0?hours=0?days=0

- GET localhost:8099/api/cryptos/{crypto}/buy?euro=100

- GET localhost:8099/api/cryptos/{crypto}/sold?quantity=100
