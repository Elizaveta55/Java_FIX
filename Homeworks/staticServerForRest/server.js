let express = require('express');
let cookieParser = require('cookie-parser');
let app = express();
app.use(cookieParser());
app.get('/users.html', function(req, res, next) {
    let token = req.cookies['Auth-Token'];
    if (!req.cookies['Auth-Token']) {
        res.redirect('/login.html')
    }
    next();
});
app.get('/products.html', function(req, res, next) {
    let token = req.cookies['Auth-Token'];
    if (!req.cookies['Auth-Token']) {
        res.redirect('/login.html')
    }
    next();
});
app.use(express.static('public'));
app.listen(80);
console.log("StaticServer started...");