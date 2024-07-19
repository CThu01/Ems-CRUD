import React from 'react'

const MainContainer = ({ children }) => {
    return (
        <div className='container h-screen w-8/12'>
            {children}
        </div>
    )
}

export default MainContainer