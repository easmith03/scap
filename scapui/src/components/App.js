import React from 'react';
import './App.css';
import StudentTable from './StudentTable';
import StudentDetail from './StudentDetail';
import PageNotFound from './PageNotFound';
import Header from './Header';
import {Route, Switch} from 'react-router-dom';
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {
  return (
    <div className="App">
      <ToastContainer autoClose={3000} hideProgressBar />
      <Header />
      <Switch>
          <Route path="/" exact component={StudentTable} />
          <Route path="/detail/:studentId" component={StudentDetail} />
          <Route path="/detail/" component={StudentDetail} />
          <Route component={PageNotFound} />
      </Switch>
     </div>
  );
}

export default App;
