import "./styles/FormScenario.css";
import { useEffect, useState } from "react";
import Task from "./Task";
import { useParams } from "react-router-dom";

const FormTasks: React.FC = () => {
  //   interface Exercise {
  //     id: number;
  //     points: number;
  //     content: string;
  //     correctAnswer: string;
  //     userAnswer: string;
  //   }

  //   interface ExerciseListItem {
  //     description: string;
  //     excerciseList: Exercise[];
  //     id: number;
  //     type: string;
  //   }

  //   interface TaskItem {
  //     id: number;
  //     description: string;
  //     excerciseList: ExerciseListItem[];
  //     type: string;
  //   }

  const { lessonId } = useParams();
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(
        `http://localhost:8080/api/v1/lessons/${lessonId}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const data = await response.json();
      setTasks(data.taskList);
      console.log(tasks);
      console.log(data);
    };

    fetchData();
  }, []); // Zmiany topicValue lub id spowodują ponowne wykonanie efektu

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log("Submitted value:");
  };

  return (
    <>
      <Task taskItem={lessonId} />
      {/* <Task />
      <Task />
      <Task /> */}
      <form onSubmit={handleSubmit}>
        <button type="submit" className="button-send">
          GENERUJ!
        </button>
      </form>
    </>
  );
};

export default FormTasks;
