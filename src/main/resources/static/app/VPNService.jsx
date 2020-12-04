import React from 'react';
import { Button, Form } from 'react-bootstrap'
import { Redirect } from 'react-router-dom'
import API from './utils/API.js'
import formatDate from './utils/Date.js'
import Datetime from 'react-datetime';

export default class VPNService extends React.Component {

    constructor(props) {
        super(props);

        const { isNote, id, name, server, password, dateExpiry } = props.vpnservice;

        this.state = {
            error: props.error,
            update: props.update,
            isGoTo: false,
            isNote: isNote,
            isEdit: false,
            id: id,
            name: name,
            server: server,
            password: password,
            dateExpiry: formatDate(dateExpiry)
        };

        this.handleDelete = this.handleDelete.bind(this);
        this.handleEdit = this.handleEdit.bind(this);

        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeServer = this.handleChangeServer.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleChangeDate = this.handleChangeDate.bind(this);

        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleClickNote = this.handleClickNote.bind(this)
    }

    handleClickNote() {
        this.setState((prevState) => ({
            ...prevState, isGoTo: true
        }))
    }

    handleDelete(event) {
        const { id } = this.state;

        API.delete('note/' + id)
            .then(res => {
                this.state.update()
            }).catch(err => {
                if (err.response) {
                    console.log(err.response)
                } else if (err.request) {
                    console.log("request")
                } else {
                    console.log("else")
                }
            });
    }

    handleEdit(event) {
        this.setState((prevState) => ({ ...prevState, isEdit: true }));
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
        this.setState((prevState) => ({ ...prevState, dateExpiry: date }));
    }

    handleUpdate(event) {
        const { id, name, server, password, dateExpiry } = this.state;

        API.post('note/update/' + id, {
            name: name,
            server: server,
            password: password,
            dateExpiry: dateExpiry
        }).then(res => {
            const { id, name, server, password, dateExpiry } = res.data;
            this.setState((prevState) => ({
                ...prevState,
                isEdit: false,
                id: id,
                name: name,
                server: server,
                password: password,
                dateExpiry: dateExpiry
            }));
        }).catch(err => {
            if (err.response) {
                this.state.error(err.response)
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
        const { isGoTo, isNote, isEdit, id, name, server, password, dateExpiry } = this.state;

        if (isGoTo) {
            return (
                <Redirect to={"/note/" + id} />
            )
        }

        if (isEdit == false) {
            return (
                <tr>
                    <td>{id}</td>
                    <td>{name}</td>
                    <td>{server}</td>
                    <td>{password}</td>
                    <td>{dateExpiry}</td>
                    <td>
                        <>
                            <Button variant="primary" size="sm" onClick={this.handleEdit}>
                                Edit
                            </Button>
                            {' '}
                            {!isNote &&
                                <Button variant="secondary" size="sm" onClick={this.handleClickNote}>
                                    Go to
                                </Button>
                            }
                            {' '}
                            <Button variant="secondary" size="sm" onClick={this.handleDelete}>
                                Delete
                            </Button>
                        </>
                    </td>
                </tr>
            );
        } else {
            return (
                <tr>
                    <td></td>
                    <td><Form.Control placeholder="name" value={this.state.name} onChange={this.handleChangeName} /></td>
                    <td><Form.Control placeholder="server" value={this.state.server} onChange={this.handleChangeServer} /></td>
                    <td><Form.Control placeholder="password" value={this.state.password} onChange={this.handleChangePassword} /></td>
                    <td><Datetime dateFormat="yyyy-mm-DD" value={this.state.dateExpiry} onChange={this.handleChangeDate} /></td>
                    <td><Button size="sm" block type="submit" onClick={this.handleUpdate}>Update</Button></td>
                </tr>
            )
        }
    }
}
