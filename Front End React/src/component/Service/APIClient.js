import axios from 'axios';
import { useLocation } from 'react-router-dom';

const getAsset = {assets: '/findAllAssets', liabilities: '/findAllLiabilities', income: '/findAllIncome', expenses: '/findAllExpenses'};
const addAsset = {assets: '/createAssetEntry', liabilities: '/createLiabilityEntry', income: '/createIncomeEntry', expenses: '/createExpenseEntry'};
const deleteAsset = {assets: '/deleteSelectedAsset', liabilities: '/deleteSelectedLiability', income: '/deleteSelectedIncome', expenses: '/deleteSelectedExpense'};
const origin = window.location.origin.replace(/:\d+$/, ":8080"); //Allows use on LAN

const usePath = (apiRequest) => {
    let path = useLocation().pathname.replace('/', '');
    return apiRequest[path];
}

const getRequest = (endpoint) => {
    return axios.get(origin + endpoint).then(response => response.data).catch(error => {
        console.log(error);
    });
};

const postRequest = (endpoint, financialItem) => {
    axios.post(origin + endpoint, financialItem).then(response => response.data).catch(error => {
        console.log(error);
    });
};

export { getRequest, postRequest, usePath };