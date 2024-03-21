import { useState, useEffect } from "react";
//import { useParams } from "react-router-dom";
import "./styles/FormScenario.css";

// interface Exercise {
//   id: number;
//   points: number;
//   content: string;
//   correctAnswer: string;
//   userAnswer: string;
// }

// interface ExerciseListItem {
//   description: string;
//   excerciseList: Exercise[];
//   id: number;
//   type: string;
// }

// interface TaskItem {
//   id: number;
//   description: string;
//   excerciseList: ExerciseListItem[];
//   type: string;
// }

const Task: React.FC<{ taskItem: string | undefined }> = ({ taskItem }) => {
  //Sconst { topicValue, id } = useParams<{ topicValue: string; id: string }>();
  const [hobbyValue, setHobbyValue] = useState("");
  const [dataValue, setDataValue] = useState({
    taskList: [{ description: "", excerciseList: [{ content: "" }] }],
  });

  const handleHobbyChange = (e: any) => {
    setHobbyValue(e.target.value);
  };

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(
        `http://localhost:8080/api/v1/lessons/${taskItem}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const data = await response.json();
      console.log(data);
      setDataValue(data);
    };

    fetchData();
  }, []); // Zmiany topicValue lub id spowodują ponowne wykonanie efektu

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log("Submitted value:", hobbyValue);
  };

  return (
    <form onSubmit={handleSubmit}>
      {dataValue.taskList.map((elem) => {
        return (
          <>
            <div className="task-tile">
              <label className="bigText">
                {JSON.stringify(elem.description)}
                {elem.excerciseList.map((el) => {
                  return (
                    <>
                      <p>{el.content}</p>
                      <input
                        type="text"
                        value={""}
                        onChange={handleHobbyChange}
                        className="input-text"
                        placeholder="answer 1"
                      />
                      <input
                        type="text"
                        value={""}
                        onChange={handleHobbyChange}
                        className="input-text"
                        placeholder="answer 2"
                      />
                    </>
                  );
                })}
              </label>
            </div>
          </>
        );
      })}
    </form>
  );
};

export default Task;
