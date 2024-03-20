import React from "react";
import Navigation from "../components/Navigation";
import FormScenario from "../components/FormScenario";

const Scenarios: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Scenariusze</h1>
      <FormScenario />
    </div>
  );
};

export default Scenarios;
