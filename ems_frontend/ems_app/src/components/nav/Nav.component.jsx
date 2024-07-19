import React from 'react'
import { Button } from "@/components/ui/button"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"

const NavComponent = () => {
    return (
        <div className='flex justify-between py-6 border-b-2 border-basic'>
            <h1 className='text-basic font-bold text-xl'>Employee Management System</h1>
            <div className='flex gap-4'>
                <Button variant="destructive">Logout</Button>
                <Avatar>
                    <AvatarImage src="https://t3.ftcdn.net/jpg/06/17/13/26/240_F_617132669_YptvM7fIuczaUbYYpMe3VTLimwZwzlWf.jpg" />
                    <AvatarFallback>CN</AvatarFallback>
                </Avatar>
            </div>
        </div>
    )
}

export default NavComponent