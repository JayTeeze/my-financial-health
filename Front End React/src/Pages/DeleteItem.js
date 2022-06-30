import { Tooltip, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import axios from 'axios';

export default function DeleteItem(props) {
    const { selected, category } = props;
    const apiMap = {assets: 'deleteSelectedAsset', liabilities: 'deleteSelectedLiability', income: 'deleteSelectedIncome', expenses: 'deleteSelectedExpense'};

    const submitHandler = (apiUrl) => {
        selected.forEach(element => {
            axios.get(`http://localhost:8080/${apiUrl}?id=${element}`)
        });
    };

    return(
        <Tooltip title="Delete">
            <IconButton onClick={() => submitHandler(apiMap[category])}>
                <DeleteIcon />
            </IconButton>
        </Tooltip>
    );
}