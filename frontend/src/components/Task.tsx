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

interface Answer {
  [index: number]: { id: number; userAnswer: string };
}

const LoadingAnimation = () => (
  <div className="loading-animation"></div> // Komponent animacji kółeczka
);

const Task: React.FC<{ taskItem: string | undefined }> = ({ taskItem }) => {
  //Sconst { topicValue, id } = useParams<{ topicValue: string; id: string }>();

  const [answers, setAnswers] = useState<Answer>({});
  const [isButtonDisabled, setIsButtonDisabled] = useState(false);

  const [dataValue, setDataValue] = useState({
    taskList: [
      {
        description: "",
        excerciseList: [{ content: "", correctAnswer: "", id: 0 }],
      },
    ],
    feedback: "",
  });

  const handleAnswerChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    index: number
  ) => {
    const { value } = e.target;
    setAnswers((prevState) => ({
      ...prevState,
      [index]: { ...prevState[index], userAnswer: value },
    }));

    const fetchData = async () => {
      await fetch(`http://localhost:8080/api/v1/exercises/${index}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userAnswer: value.trim(),
        }),
      });
    }; // Zmiany topicValue lub id spowodują ponowne wykonanie efektu

    fetchData();
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
    console.log("Submitted value: ", answers);
    setIsButtonDisabled(true);
    const fetchData = async () => {
      const response = await fetch(
        `http://localhost:8080/api/v1/lessons/submit/${taskItem}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const data = await response.json();
      setIsButtonDisabled(false);
      setDataValue(data);
    };

    fetchData();
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        {/* 
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
                        {el.correctAnswer.split(",").map((el) => {
                          return (
                            <input
                              type="text"
                              value={el}
                              onChange={handleHobbyChange}
                              className="input-text"
                              placeholder="answer"
                            />
                          );
                        })}
                      </>
                    );
                  })}
                </label>
              </div>
            </>
          );
        })}

        <button type="submit" className="button-send">
          GENERUJ!
        </button>
      </form> */}
        {dataValue.taskList.map((elem, index) => {
          return (
            <div className="task-tile" key={index}>
              <label className="bigText">
                {JSON.stringify(elem.description)}
                {elem.excerciseList.map((el, subIndex) => {
                  return (
                    <div key={subIndex}>
                      <p style={{ paddingLeft: "20px", paddingRight: "20px" }}>
                        {el.content}
                      </p>
                      <input
                        key={el.id}
                        type="text"
                        value={answers[el.id]?.userAnswer || ""}
                        onChange={(e) => handleAnswerChange(e, el.id)}
                        className="input-text"
                        placeholder="answer"
                      />
                    </div>
                  );
                })}
              </label>
            </div>
          );
        })}

        <button
          type="submit"
          className="button-send"
          disabled={isButtonDisabled}
        >
          GENERUJ!
        </button>
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            padding: "15px",
          }}
        >
          {isButtonDisabled && <LoadingAnimation />}
        </div>

        <div
          className="task-tile"
          style={{ paddingLeft: "20px", paddingRight: "20px" }}
        >
          <label className="bigText">{dataValue.feedback || ""}</label>
        </div>
      </form>
    </>
  );
};

export default Task;
