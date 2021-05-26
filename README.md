# bitcoin-price-service
I'm trying to get bitcoin-usdt price from Binance Api, then show the price in the response

### Data Source (Binance API)
#### Rate Data
I'm using a response DTO instead of Object to validate data inside test. Ticker Price API can be found here: https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#symbol-price-ticker

#### Historical Data
I'm using Kline Endpoint for getting history data, the documentation can be found here: https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#klinecandlestick-data

### Test
I'm using Junit 5 and black box testing here