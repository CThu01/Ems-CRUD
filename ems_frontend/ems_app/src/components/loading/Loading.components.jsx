import React from 'react'
import { Loader2 } from "lucide-react"

const LoadingComponents = () => {
    return (
        <div className='flex justify-center items-center w-full mx-auto mt-auto'>
            <Loader2 className="mr-2 h-4 w-4 animate-spin " />
        </div>

    )
}

export default LoadingComponents