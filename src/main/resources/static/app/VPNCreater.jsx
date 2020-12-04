import React from 'react';
import API from "./utils/API.js"
import { Button, Form } from 'react-bootstrap'
import Datetime from 'react-datetime';

export default class VPNCreater extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            error: props.error,
            update: props.update,
            name: '',
            server: '',
            password: '',
            dateExpiry: ''
        }

        this.handleChangeName = this.handleChangeName.bind(this)
        this.handleChangeServer = this.handleChangeServer.bind(this)
        this.handleChangePassword = this.handleChangePassword.bind(this)
        this.handleChangeDate = this.handleChangeDate.bind(this)

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChangeName(event) {
        this.setState((prevState) => ({ ...prevState, name: event.target.value }));
    }

    handleChangeServer(event) {
        this.setState((prevState) => ({ ...prevState, server: event.target.value }));
    }

    handleChangePassword(event) {
        this.setState((prevState) => ({ ...prevState, password: event.target.value }));
    }

    handleChangeDate(date) {
        this.setState((prevState) => ({ ...prevState, dateExpiry: date}));
    }

    handleSubmit(event) {
        const { name, server, password, dateExpiry } = this.state;

        API.put('note', {
            name: name,
            server: server,
            password: password,
            dateExpiry: dateExpiry
        }).then(res => {
            this.state.update(res.data);
        }).catch(err => {
            if (err.response) {
                this.state.error(err.response.data)
            } else if (err.request) {
                alert('Unknown error \'error.request\', see in console')
                console.log(err)
            } else {
                alert('Unknown error, see in console')
                console.log(err)
            }
        });
    }

    render() {
        return (
            <tr>
                <td></td>
                <td><Form.Control value={this.state.name} placeholder="name" onChange={this.handleChangeName} /></td>
                <td><Form.Control value={this.state.server} placeholder="server" onChange={this.handleChangeServer} /></td>
                <td><Form.Control value={this.state.password} placeholder="password" onChange={this.handleChangePassword} /></td>
                <td><Datetime dateFormat="yyyy-mm-DD" value={this.state.dateExpiry} onChange={this.handleChangeDate} /></td>
                <td><Button size="sm" block type="submit" onClick={this.handleSubmit}>Add</Button></td>
            </tr>
        );
    }
}