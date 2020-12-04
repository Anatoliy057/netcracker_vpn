import Notes from './app/Notes.jsx';
import Note from './app/Note.jsx';
import ReactDom from "react-dom";
import React from "react";
import { BrowserRouter, Switch, Route } from 'react-router-dom'

import 'bootstrap/dist/css/bootstrap.min.css';
import "react-datetime/css/react-datetime.css";

ReactDom.render(
    <BrowserRouter>
        <Switch>
            <Route path="/note/:id" component={Note} />
            <Route path="/note" component={Notes} />
        </Switch>
    </BrowserRouter>,
    document.getElementById('react')
);
