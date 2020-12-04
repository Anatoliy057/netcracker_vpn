import React from "react";
import API from "./utils/API.js"
import { Table, Alert } from 'react-bootstrap'
import VPNService from './VPNService.jsx'
import VPNCreater from "./VPNCreater.jsx"

export default class Notes extends React.Component {

    constructor(props) {
        super(props);
        this.state = { services: [], errors: [] };

        this.updateServices = this.updateServices.bind(this);
        this.addService = this.addService.bind(this)
        this.errorMsg = this.errorMsg.bind(this)
    }

    componentDidMount() {
        this.updateServices();
    }

    updateServices() {
        this.setState({ services: [], errors: [] });
        API.post(`note`)
            .then(res => {
                const services = res.data;
                this.setState({ services });
            });
    }

    addService(service) {
        this.setState((prevState) => ({
            errors: [], services: [...prevState.services, service]
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
            console.log(error)
        }

        this.setState((prevState) => ({ ...prevState, errors: errors }))
    }

    render() {

        const { errors, services } = this.state;

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
                        {services.map((service) => <VPNService error={this.errorMsg} update={this.updateServices} vpnservice={service} />)}
                        <VPNCreater error={this.errorMsg} update={this.addService} isNote={false}></VPNCreater>
                    </tbody>
                </Table>
                {errors.sort().map((error, idx) => (
                    <Alert key={idx} variant={'danger'}>
                        {error}
                    </Alert>
                ))}
            </div>
        );
    }
}
