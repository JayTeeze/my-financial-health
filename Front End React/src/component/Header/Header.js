import { AppBar, Box, Button, Toolbar, Typography } from '@mui/material';
import TempDrawer from './../Drawer/Drawer';
import { Link } from 'react-router-dom';

function Header() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <TempDrawer />
                    <Button color='inherit' size='large' component={Link} to='/' sx={{ alignSelf: 'left' }} >
                        My Financial Health
                    </Button>
                    <Typography component='div' sx={{ flexGrow: 1 }} />
                    <Button color="inherit">Login</Button>
                </Toolbar>
            </AppBar>
        </Box>
    );
}
  
export default Header;