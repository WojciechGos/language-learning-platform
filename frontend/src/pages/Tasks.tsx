import React from "react";
import Navigation from "../components/Navigation";
import FormTasks from "../components/FormTasks";

const Scenarios: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Zadania</h1>
      <FormTasks />
    </div>
  );
};

export default Scenarios;
