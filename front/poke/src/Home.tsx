import React from 'react';
import  {HashRouter, Switch, Route}  from 'react-router-dom';
import './App.css';
import {SearchPage} from "./pages/Search";
import {SearchResult} from "./pages/SearchResult";
import {NotFoundPage} from "./pages/NotFoundPage";

export const HomeComponent = () => {
    // const proxy = require('http-proxy-middleware');
    //
    // module.exports = function(app:any) {
    //     app.use(proxy('/api', { target: 'http://localhost:8080/' }));
    // };
  return (
   <>
       <div><h2></h2>
     <HashRouter>
         <Switch>
        <Route path="/" exact={true} component={SearchPage}></Route>
             <Route path="/results" component={SearchResult}></Route>
             <Route path="/404" component={NotFoundPage}></Route>
             <Route path="/500" component={NotFoundPage}></Route>
         </Switch>
     </HashRouter>
       </div>
   </>
  );
}


