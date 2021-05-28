# Bitcoin price (rate and history) service
I'm trying to:
1. get bitcoin-usd rate periodically (based on the config) then show the rate in the response of "rates" endpoint.
2. return the history of any coin based on the passed variable to "histories" endpoint. I prefer "startTime", "endTime" as Binance API because they are in timestamp format and can contain time (not just date)

### Technologies
I'm using:
- Java11
- Spring Boot 2
- Reactive Streams (Reactor) to having an alternative for multi-threading deadlocks and other problems
- Lombok

### External Data Source (Binance API)
#### Rate Data
I'm using a response DTO instead of Object to validate data inside test. Ticker Price API can be found here: https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#symbol-price-ticker

#### Historical Data
I'm using Kline Endpoint for getting history data, we could use object mapping to replace numeric keys with human-readable keys (but I did not and just mirrored Binance response), like "closeTime". The documentation can be found here: https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#klinecandlestick-data

### Exceptions
- "rates" endpoint will check the cache for fetching bitcoin, if it's not there we will get 204 as the response statusCode.
- For "histories" API we will get a 404 statusCode if it could not find the symbol.  

### Tests
I'm using Junit 5 and black box testing here.

### Structure
I'm using a runner to be run first based on time period (in configuration) then put the bitcoin rate on ratesCache to be used later on by rates endpoint.

### Going to production
- The cache layer can be improved by Spring cache manager or distributed hash tables or using a key/value database like Redis (for having a better choice we need more information about the product)
- Swagger could be also used to improve the documentation part.
