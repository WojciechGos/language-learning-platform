import React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Classes from "./pages/Classes";
import Scenarios from "./pages/Scenarios";
import Stats from "./pages/Stats";
import Activities from "./pages/Activities";
import Assistant from "./pages/Assistant";
import Tutor from "./pages/Tutor";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Dashboard />,
    errorElement: <div>ERROR 404 NOT FOUND</div>, //zrobic error screen
  },
  {
    path: "/classes",
    element: <Classes />,
  },
  {
    path: "/activities",
    element: <Activities />,
  },
  {
    path: "/stats",
    element: <Stats />,
  },
  {
    path: "/scenarios",
    element: <Scenarios />,
  },
  {
    path: "/assistant",
    element: <Assistant />,
  },
  {
    path: "/tutor",
    element: <Tutor />,
  },
]);

const App: React.FC = () => {
  return <RouterProvider router={router} />;
};

export default App;