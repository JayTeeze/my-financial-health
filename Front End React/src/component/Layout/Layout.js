import { Route, Routes } from 'react-router-dom';
import Welcome from '../Welcome/Welcome';
import Header from './../Header/Header';

function Layout() {

  const toggleRoute = () => {
    if (localStorage.getItem('loggedInUser')) {
      return (
        <div className='Layout mb-5'>
          <Header />
        </div>
      )
    } else {
      return (
        <div className='Layout mt-5 text-center'>
          <h1>My Financial Health</h1>
          <Routes>
            <Route path='/' element={<Welcome />} />
          </Routes>
        </div>
      )
    }
  };
    return toggleRoute();
}
  
export default Layout;