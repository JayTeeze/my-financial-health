import { Route, Routes } from 'react-router-dom';
import Dashboard from './component/Dashboard/DashboardLayout';
import Header from './component/Header/Header';
import PageLayout from './Pages/PageLayout';

const AppRoutes = () => {
  return (
    <div>
      <Header />
      <Routes>
        <Route path='/' element={<Dashboard />} />
        <Route path='/*' element={<PageLayout />} />
      </Routes>
    </div>
  );
}

export default AppRoutes;