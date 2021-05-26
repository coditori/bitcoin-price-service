# bitcoin-price-service
I'm trying to:
1- get bitcoin-usdt price from Binance Api periodically (based on the config) then show the rate in the response rates response.
2- return the history of any coin based on the passed variable to histories endpoint. I prefer "startTime", "endTime" as Binance API because they are in timestamp format and can contain time (not just date) 

### Data Source (Binance API)
#### Rate Data
I'm using a response DTO instead of Object to validate data inside test. Ticker Price API can be found here: https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#symbol-price-ticker

#### Historical Data
I'm using Kline Endpoint for getting history data, the documentation can be found here: https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#klinecandlestick-data

### Test
I'm using Junit 5 and black box testing here

### Going to production
The cache layer can be improved by distributed hash tables or using a key/value database like Redis.
Swagger could be also used to improve the documentation part.