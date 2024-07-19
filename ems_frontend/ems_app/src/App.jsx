import React from 'react'
import MainContainer from './components/container/Main.container'
import NavComponent from './components/nav/Nav.component'
import TableComponent from './components/table/Table.component'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import EmployeeFormComponent from './components/EmployeeForm.component'

const App = () => {
    return (
        <MainContainer>
            <NavComponent />
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<TableComponent />} />
                    <Route path='/create' element={<EmployeeFormComponent />} />
                </Routes>
            </BrowserRouter>
        </MainContainer>
    )
}

export default App