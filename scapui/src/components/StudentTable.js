import React, {useEffect, useState} from 'react';
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css
import StudentApi from './service/StudentApi';
import './StudentTable.css';
import {Link} from 'react-router-dom';
import {toast} from 'react-toastify';

function StudentTable(props) {

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState();
    const [items, setItems] = useState([]);
    const [updateTable, setUpdateTable] = useState(false);

    useEffect(() => {
        StudentApi.call("http://localhost:7080/students")
            .getAll()
            .then(
                (result) => {
                    setLoading(false);
                    setItems(result);
                },
                (error) => {
                    setLoading(false);
                    setError(error);
                }
            )
    }, [updateTable]);

    function onDelete(event, id) {
        event.preventDefault();

        confirmAlert({
            title: 'Confirm Delete',
            message: 'Are you sure to do this.',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => StudentApi.call("http://localhost:7080/students")
                        .delete(id)
                        .then(() => {
                            toast.success("Student Deleted");
                            setUpdateTable(!updateTable);
                        })
                        .catch((err) => {
                            toast.error("Student Deleted Error: " + err.message);
                            setUpdateTable(!updateTable);
                        })

                },
                {
                    label: 'No'
                }
            ]
        });
    }

    function getPage() {
        if (error) {
            return <div>Error: {error.messages}</div>;
        } else if (loading) {
            return <div>Loading...</div>;
        } else {
            return (
                <div className="StudentTable">
                <Link to="/detail" className="button"> Add User </Link>
                <table>
                    <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead><tbody>
                {items.map(item => (
                    <tr key={item.id}>
                        <td>{item.firstName}</td><td>{item.lastName}</td><td>{item.email}</td>
                        <td><Link to={"/detail/" + item.id} className="button"> Update User </Link></td>
                        <td><Link to={"/"} onClick={(e) => { onDelete(e, item.id)}} className="button"> Delete User </Link></td>
                    </tr>
                ))}
                </tbody>
                </table>
                </div>
            );
        }
    }

    return (
        <>
        {getPage()}
        </>
    )
}

export default StudentTable;