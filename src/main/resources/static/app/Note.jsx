import React from "react";
import API from "./utils/API.js"
import { Table, Alert, Button } from 'react-bootstrap'
import { withRouter } from "react-router-dom";
import VPNService from './VPNService.jsx'

export default class Note extends React.Component {

    constructor(props) {
        super(props);
        this.state = { id: props.match.params.id, service: {}, errors: [] };

        this.updateService = this.updateService.bind(this);
        this.addService = this.addService.bind(this)
        this.errorMsg = this.errorMsg.bind(this)
        this.handleClickHome = this.handleClickHome.bind(this)
    }

    componentDidMount() {
        this.updateService();
    }

    handleClickHome() {
        this.props.history.push("/note");
    }

    updateService() {
        const { id } = this.state;
        this.setState({ id: id, service: {}, errors: [] });
        API.post(`note/${id}`)
            .then(res => {
                this.setState((prevState) => ({ ...prevState, errors: [], service: res.data }));
            }).catch(err => {
                if (err.response) {
                    this.errorMsg(err.response.data)
                } else if (err.request) {
                    alert('Unknown error \'error.request\', see in console')
                    console.log(err)
                } else {
                    alert('Unknown error, see in console')
                    console.log(err)
                }
            });
    }

    addService(service) {
        this.setState((prevState) => ({
            ...prevState, errors: [], service: service
        }));
    }

    errorMsg(error) {
        const errors = []
        if (error.message) {
            errors.push(error.message)
        } else if (error.fieldValidationErrors) {
            error.fieldValidationErrors.map((e) => (
                errors.push(e.field + ' : ' + e.message)
            ))
        } else {
            alert('Unknown error, see in console')
            console.log(error)
        }

        this.setState((prevState) => ({ ...prevState, errors: errors }))
    }

    render() {

        const { errors, service } = this.state;
        if (service && Object.keys(service).length != 0) {
            return (
                <div>
                    <Table size="sm" striped="true" bordered="true" hover="true">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Server</th>
                                <th>Password</th>
                                <th>Date expiry</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <VPNService error={this.errorMsg} update={this.updateService} vpnservice={service} isNote={true} />
                        </tbody>
                    </Table>
                    {errors.sort().map((error, idx) => (
                        <Alert key={idx} variant={'danger'}>
                            {error}
                        </Alert>
                    ))}
                    <Button variant="secondary" size="lg" block onClick={this.handleClickHome}>
                        Home
                    </Button>
                </div>
            );
        } else {
            return (
                <div>
                    {errors.sort().map((error, idx) => (
                        <Alert key={idx} variant={'danger'}>
                            {error}
                        </Alert>
                    ))}
                    <Button variant="secondary" size="lg" block onClick={this.handleClickHome}>
                        Home
                    </Button>
                </div>
            )
        }
    }
}
