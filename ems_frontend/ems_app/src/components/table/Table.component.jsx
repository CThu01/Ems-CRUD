import React from 'react'
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table"
import { useDeleteEmployeeMutation, useGetEmployeeQuery } from '../../store/Apiservice/endpoints/Ems.endpoints'
import LoadingComponents from '../loading/Loading.components';
import { Button } from "@/components/ui/button"
import { useNavigate } from 'react-router-dom';


const TableComponent = () => {

    const nav = useNavigate();
    const { isLoading, isError, isSuccess, data, error } = useGetEmployeeQuery();
    // console.log(error);
    const [deleteFun, deleteResult] = useDeleteEmployeeMutation();


    const handleCreateEmployee = () => {
        nav("/create")
    }

    const handleUpdate = (employee) => {
        // console.log(employee)
        nav("/create", { state: employee })
    }

    const handleDelete = (id) => {
        deleteFun(id);
    }

    return (
        <div className='mt-10'>
            <Button onClick={handleCreateEmployee} className="bg-create">Create Employee</Button>
            <Table className="h-auto mt-2">
                <TableHeader>
                    <TableRow>
                        <TableHead className="text-basic">Id</TableHead>
                        <TableHead className="text-basic">First Name</TableHead>
                        <TableHead className="text-basic">Last Name</TableHead>
                        <TableHead className="text-basic">Email</TableHead>
                    </TableRow>
                </TableHeader>

                <TableBody>
                    {
                        isError ?
                            <TableRow className="h-[400px]">
                                <TableCell colSpan="4">
                                    <p className='text-center text-danger'>
                                        Status : {error.status}
                                    </p>
                                    <p className='text-center text-danger'>
                                        {error.error}
                                    </p>

                                </TableCell>
                            </TableRow>
                            :
                            <>
                                {
                                    isLoading ?
                                        <TableRow >
                                            <TableCell colSpan="4"><LoadingComponents /></TableCell>
                                        </TableRow>
                                        :
                                        <>
                                            {
                                                data?.payload.map((e) => (
                                                    <TableRow key={e.id} className="h-2">
                                                        <TableCell className="text-basic">{e.id}</TableCell>
                                                        <TableCell className="text-basic">{e.firstName}</TableCell>
                                                        <TableCell className="text-basic">{e.lastName}</TableCell>
                                                        <TableCell className="text-basic">{e.email}</TableCell>
                                                        <TableCell>
                                                            <div className='space-x-2'>
                                                                <Button onClick={() => handleUpdate(e)} className="bg-create">Update</Button>
                                                                <Button onClick={() => handleDelete(e.id)} className="bg-danger">Delete</Button>
                                                            </div>
                                                        </TableCell>
                                                    </TableRow>))
                                            }
                                        </>
                                }
                            </>
                    }


                </TableBody>
            </Table>
        </div>
    )
}

export default TableComponent