(function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var Unit = Kotlin.kotlin.Unit;
  function main$lambda(req, res) {
    res.type('text/plain');
    return res.send('i am a beautiful butterfly');
  }
  function main$lambda_0() {
    println('Listening on port 3000');
    return Unit;
  }
  function main(args) {
    var express = require('express');
    var app = express();
    app.get('/', main$lambda);
    app.listen(3000, main$lambda_0);
  }
  function UiController() {
  }
  UiController.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'UiController',
    interfaces: []
  };
  var package$assetoverview = _.assetoverview || (_.assetoverview = {});
  package$assetoverview.main_kand9s$ = main;
  var package$ui = package$assetoverview.ui || (package$assetoverview.ui = {});
  package$ui.UiController = UiController;
  main([]);
  Kotlin.defineModule('index', _);
  return _;
}(module.exports, require('kotlin')));
