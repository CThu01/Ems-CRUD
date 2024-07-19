import { EmsService } from "../Ems.service";


const emsEndpoints = EmsService.injectEndpoints({
    endpoints: (builder) => ({

        createEmployee: builder.mutation({
            query: (employeeForm) => ({
                url: "/employee",
                method: "POST",
                body: employeeForm
            }),
            invalidatesTags: ["/employee"]
        }),

        getEmployee: builder.query({
            query: () => "/employee",
            providesTags: ["/employee"]
        }),

        getEmployeeById: builder.query({
            query: (id) => ({
                url: `/employee/${id}`,
                method: "GET"
            }),
            providesTags: ["/employee"]
        }),

        updateEmployee: builder.mutation({
            query: (employeeForm) => ({
                url: `/employee/${employeeForm.id}`,
                method: "PUT",
                body: employeeForm
            }),
            invalidatesTags: ["/employee"]
        }),

        deleteEmployee: builder.mutation({
            query: (id) => ({
                url: `/employee/${id}`,
                method: "DELETE"
            }),
            invalidatesTags: ["/employee"]
        })
    })
})


export const { useCreateEmployeeMutation, useGetEmployeeQuery, useGetEmployeeByIdQuery, useUpdateEmployeeMutation, useDeleteEmployeeMutation } = emsEndpoints;