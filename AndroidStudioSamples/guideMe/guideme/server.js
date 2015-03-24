var express = require('express'),
    bodyParser = require('body-parser'),
    poi = require('./poi.js');

var app = express();

app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());

app.post('/poi', poi.addPOI);
app.get('/poi', poi.listAll);
app.get('/poi/:id', poi.getPOI);
app.get('/poi/range/:latt/:logt/:distance', poi.getPOIByDistance);
app.get('/poi/range/:latt/:logt/:distance/:type', poi.getPOIByType);


var server = app.listen(3000, function () {

    var host = server.address().address
    var port = server.address().port

    console.log('Listening at http://%s:%s', host, port)

});
