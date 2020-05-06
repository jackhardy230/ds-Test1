import React from 'react'
import Table from 'react-bootstrap/Table'

const Sensor = ({ sensordata }) => {
    const badcolor = {
        color: "red"
    };
    const goodcolor = {
        color: "green"
    };

    return (
        <div>
            <Table striped bordered hover>

                <thead>
                    <tr>
                        <th>Sensor ID</th>
                        <th>Floor No.</th>
                        <th>Room No.</th>
                        <th>Smoke Level</th>
                        <th>CO2 Level</th>
                        <th>Date</th>
                        <th>Time</th>
                    </tr>
                </thead>
                <tbody>
                    {sensordata.map((sensord) => (
                        <tr>
                            <td>{sensord.sensor.sensorid}</td>
                            <td>{sensord.sensor.floorNo}</td>
                            <td>{sensord.sensor.roomNo}</td>
                            <td style={sensord.smokeLevel > 5 ? badcolor : goodcolor} >{sensord.smokeLevel}</td>
                            <td style={sensord.co2Level > 5 ? badcolor : goodcolor}>{sensord.co2Level}</td>
                            <td>{sensord.date}</td>
                            <td>{sensord.time}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    )
};

export default Sensor