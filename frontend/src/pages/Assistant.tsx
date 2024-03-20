import React from "react";
import Navigation from "../components/Navigation";
import AssistantBar from "../components/AssistantBar";

const Assistant: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Asystent</h1>
      <AssistantBar />
    </div>
  );
};

export default Assistant;
