import React from 'react'
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { ErrorMessage, Formik, Form } from 'formik';
import { useLocation, useNavigate } from 'react-router-dom';
import { useCreateEmployeeMutation, useUpdateEmployeeMutation } from '../store/Apiservice/endpoints/Ems.endpoints';
import * as yup from "yup"
import LoadingComponents from './loading/Loading.components';


const EmployeeFormComponent = () => {

    const nav = useNavigate();
    const { state } = useLocation();
    const [createFun, resultData] = useCreateEmployeeMutation();
    const [updateFun, updateData] = useUpdateEmployeeMutation();
    // console.log(resultData);

    // console.log(state)

    const initialValue = state ? state : {
        firstName: "",
        lastName: "",
        email: ""
    }

    const validationSchema = yup.object({
        firstName: yup.string().required("first name is required"),
        lastName: yup.string().required("last name is required"),
        email: yup.string().email("invalid email").required("email is required")
    })

    const handleCancel = () => {
        nav("/");
    }

    const handleSubmit = (value) => {
        console.log("Handle submit is working")
        console.log(value)

        if (state) {
            updateFun(value)
        } else {
            createFun(value)
        }
        nav("/");
    }

    return (
        <div className='w-full flex justify-center m-10'>
            <div className='w-3/6 border-2 border-basic rounded p-8 '>
                <Formik
                    initialValues={initialValue}
                    validationSchema={validationSchema}
                    onSubmit={handleSubmit}
                >
                    {({ values, handleBlur, handleChange, isSubmitting }) => (
                        <Form >
                            <div className='space-y-1 mb-4 mt-2'>
                                <Label>First Name</Label>
                                <Input className="" type="text" name="firstName" value={values.firstName} onChange={handleChange} onBlur={handleBlur} placeholder="Charlies" />
                                <ErrorMessage component={"p"} name='firstName' className='text-danger' />
                            </div>
                            <div className='space-y-1 mb-4'>
                                <Label>Last Name</Label>
                                <Input type="text" name="lastName" value={values.lastName} onChange={handleChange} onBlur={handleBlur} placeholder="Danny" />
                                <ErrorMessage component={"p"} name='lastName' className='text-danger' />
                            </div>
                            <div className='space-y-1 mb-4'>
                                <Label>Email</Label>
                                <Input type="email" name="email" value={values.email} onChange={handleChange} onBlur={handleBlur} placeholder="charlies@gmail.com" />
                                <ErrorMessage component={"p"} name='email' className='text-danger' />
                            </div>

                            <div className='space-x-2'>
                                <Button onClick={handleCancel} variant="destructive">Cancle</Button>
                                <Button type="submit" className="bg-create text-center" disabled={isSubmitting} >
                                    {state ? "Update" : "Create"} &nbsp;
                                    {
                                        isSubmitting && (<LoadingComponents />)
                                    }
                                </Button>
                            </div>
                        </Form>
                    )}
                </Formik>
            </div>
        </div>

    )
}

export default EmployeeFormComponent