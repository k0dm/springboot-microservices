import axios from 'axios'

const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/api/employees/byId/1"

const EMPLOYEE_ID = 1

class EmployeeService{

    async getEmployee() {
        try {
            const response = await axios.get(EMPLOYEE_SERVICE_BASE_URL);
            return response.data; // Return the response data
        } catch (error) {
            // Handle errors here, e.g., log the error or throw it
            throw error;
        }
    }
}

export default new EmployeeService