import React from "react";
import Navigation from "../components/Navigation";
import FormLesson from "../components/FormLesson";

const Lesson: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Scenariusze</h1>
      <FormLesson />
    </div>
  );
};

export default Lesson;
