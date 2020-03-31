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

    const isCreate = (props.match.params && props.match.params.studentId) ? false : true;

    useEffect(() => {

        if (isCreate) {
            setLoading(false);
            return;
        }

        StudentApi.call("http://localhost:7080/students")
            .getOne(props.match.params.studentId)
            .then(
                (result) => {
                    setStudent(result);
                    setLoading(false);
                },
                (error) => {
                    console.log('error:', error);
                    setLoading(false);
                    setError(error.message);
                }
            );

    }, [isCreate, props.match.params.studentId]);

    function handleChange(event) {
        const studentMod = {...student, [event.target.name]: event.target.value};
        setStudent(studentMod);
    }

    function handleSubmit(event) {
        event.preventDefault();

        if (isCreate) {
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
                        setError(error.message);
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
                        setError(error.message);
                    }
                );
        }
    }

    function getPageError() {
        if (error) {
            return <div className="error">Error: {error}</div>;
        }
    }

    function getPage() {
        if (loading) {
            return <div>Loading...</div>;
        } else {
            return (
                <div className="StudentDetail">
                    <h2>Student {(isCreate) ? "Create" : "Update"}</h2>
                    {getPageError()}

                    <form onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="firstNameId" className="inputLabel">First Name: </label><input id="firstNameId" type="text" name="firstName" value={student.firstName} onChange={handleChange}/>
                        </div>
                        <div>
                            <label htmlFor="lastNameId" className="inputLabel">Last Name: </label><input id="lastNameId" type="text" name="lastName" value={student.lastName} onChange={handleChange}/>
                        </div>
                        <div>
                            <label htmlFor="emailId" className="inputLabel">Email: </label><input id="emailId" type="text" name="email" value={student.email} onChange={handleChange}/>
                        </div>

                        <div className="formButton">
                            <input type="submit" value={(isCreate) ? "Create" : "Update"} />
                            <input type="button" onClick={(e) => props.history.push("/")} value="Cancel" className="pushRight"/>
                        </div>
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

