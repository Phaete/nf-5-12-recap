import {ToDo} from "../../types/ToDo.ts";
import './TodoCard.css';

export default function TodoCard(props : ToDo) {
    return (
		<div className={"card"}>
			<div>
				<p>
					{props.description}
				</p>
				<p>
					{props.status}
				</p>
			</div>
			<div>
				<span>
					<button>Edit</button>
				</span>
				<span>
					<button>Delete</button>
				</span>
			</div>
		</div>
	)
}