import React, {useEffect, useState} from 'react';
import StudentApi from './service/StudentApi';
import './StudentDetail.css';
function StudentDetail(props) {

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState();
    const [student, setStudent] = useState({
        id: null,
        firstName: "",
        lastName: "",
        email: ""
    });

    useEffect(() => {

        if (!props.match.params || !props.match.params.studentId) {
            setLoading(false);
            return;
        }

        StudentApi.call("http://localhost:7080/students")
            .getOne(props.match.params.studentId)
            .then(
                (result) => {
                    setLoading(false);
                    setStudent(result);
                },
                (error) => {
                    setLoading(false);
                    setError(error);
                }
            );

    }, []);

    function handleChange(event) {
        const studentMod = {...student, [event.target.name]: event.target.value};
        setStudent(studentMod);
    }

    function handleSubmit(event) {
        event.preventDefault();

        if (!props.match.params || !props.match.params.studentId) {
            StudentApi.call("http://localhost:7080/students")
                .create({
                    firstName: student.firstName,
                    lastName: student.lastName,
                    email: student.email
                }).then(
                    (result) => {
                        props.history.push("/");
                    },
                    (error) => {
                        console.log("ERROR: ", error);
                    }
                );

        } else {

            StudentApi.call("http://localhost:7080/students")
                .update(props.match.params.studentId, {
                    firstName: student.firstName,
                    lastName: student.lastName,
                    email: student.email
                })
                .then(
                    (result) => {
                        props.history.push("/");
                    },
                    (error) => {
                        console.log("ERROR: ", error);
                    }
                );
        }
    }

    function getPage() {
        if (error) {
            return <div>Error: {error.messages}</div>;
        } else if (loading) {
            return <div>Loading...</div>;
        } else {
            return (
                <div className="StudentDetail">
                    <h2>Student {(props.match.params && props.match.params.studentId) ? "Update" : "Create"}</h2>
                    <form onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="firstNameId">First Name: </label><input id="firstNameId" type="text" name="firstName" value={student.firstName} onChange={handleChange}/>
                        </div>
                        <div>
                            <label htmlFor="lastNameId">Last Name: </label><input id="lastNameId" type="text" name="lastName" value={student.lastName} onChange={handleChange}/>
                        </div>
                        <div>
                            <label htmlFor="emailId">Email: </label><input id="emailId" type="text" name="email" value={student.email} onChange={handleChange}/>
                        </div>
                        <input type="submit" value={(props.match.params && props.match.params.studentId) ? "Update" : "Create"}/>
                    </form>
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

export default StudentDetail;

