import { Box, IconButton, List, ListItemIcon, ListItemText, Drawer, ListItem, ListSubheader, Divider } from "@mui/material";
import * as React from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import PaidOutlinedIcon from '@mui/icons-material/PaidOutlined';
import { Link } from 'react-router-dom';


export default function TempDrawer() {
    const [state, setState] = React.useState({left: false});
  
    const toggleDrawer = (open) => (event) => {
      if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
        return;
      }
  
      setState({ ...state, left: open });
    };
  
    const list = () => (
      <Box
        sx={{ width: 250 }}
        role="presentation"
        onClick={toggleDrawer(false)}
        onKeyDown={toggleDrawer(false)}
      >
        <List subheader={<ListSubheader>Categories</ListSubheader>}>
          <Divider />
          {['Assets', 'Liabilities', 'Income', 'Expenses'].map((text, index) => (
            <ListItem button component={Link} key={text} to={`/${text.toLowerCase()}`}>
              <ListItemIcon>
                {index % 2 === 0 ? <PaidOutlinedIcon color="success" /> : <PaidOutlinedIcon color="error" />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItem>
          ))}
        </List>
      </Box>
    );
  
    return (
      <div>
            <React.Fragment>
                <IconButton
                    size="large"
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    sx={{ mr: 2 }}
                    onClick={toggleDrawer(true)}
                >
                    <MenuIcon />
                </IconButton>
                <Drawer
                open={state.left}
                onClose={toggleDrawer(false)}
                >
                    {list()}
                </Drawer>
            </React.Fragment>
      </div>
    );
}