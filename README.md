# bitcoin-price-service
I'm trying to get bitcoin-usdt price from Binance Api, then show the price in the response

### Getting Bitcoin
We just have one and only one symbol here "BTCUSDT" then we can omit getting any URL param/query from the user side. In case of emergency (if any error happen during getting data) the api will try 20 times every 20 seconds to get the data and there is no need for sending request again.

### Test
I'm using Junit 5 and black box testing here