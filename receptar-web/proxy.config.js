module.exports = {
  '/api/*': {
    target: 'http://localhost:8080',
    secure: false,
    logLevel: 'debug',
    // auth: 'admin:admin',
   /* agent: new Agent({
      maxSockets: 100,
      keepAlive: true,
      maxFreeSockets: 10,
      keepAliveMsecs: 100000,
      timeout: 6000000,
      freeSocketTimeout: 90000
    }),
    "onProxyRes": function (proxyRes, req, res) {
      // proxyRes.headers['Access-Control-Allow-Headers'] = 'Authorization';
      // var key = 'www-authenticate';
      // proxyRes.headers[key] = proxyRes.headers[key] && proxyRes.headers[key].split(',');
    }*/
  }
};
