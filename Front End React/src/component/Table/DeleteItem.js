import { Tooltip, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { getRequest, postRequest } from '../Service/APIClient';
import { useNavigate } from 'react-router-dom';

export default function DeleteItem({ selected, financialItems, setFinancialItems, setSelected }) {
    const navigate = useNavigate();

    const submitHandler = () => {
        selected.length > 1 ? postRequest('/deleteSelectedFinancials', selected) : getRequest(`/deleteSelectedFinancial?id=${selected[0]}`)
        // let tempFinancialItems = [...financialItems];
        // let index;

        // selected.forEach(id => {
        //     getRequest(`/deleteSelectedFinancial?id=${id}`);
        //     index = tempFinancialItems.indexOf(item => item.id === id);
        //     tempFinancialItems.splice(index, 1);
        // });
        // setFinancialItems(tempFinancialItems);
        setSelected([]);
        navigate(0);
    };

    return(
        <Tooltip title="Delete">
            <IconButton onClick={() => submitHandler()}>
                <DeleteIcon />
            </IconButton>
        </Tooltip>
    );
}