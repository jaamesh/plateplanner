import React, { useState } from "react";
import {
    MDBBtn,
    MDBModal,
    MDBModalDialog,
    MDBModalContent,
    MDBModalHeader,
    MDBModalTitle,
    MDBModalBody,
    MDBModalFooter,
} from 'mdb-react-ui-kit';
import Button from "./Button";

export default function AddRecipeToMealPlan(props) {
    const [basicModal, setBasicModal] = useState(false);
    const [error, setError] = useState(null);

    const toggleOpen = () => setBasicModal(!basicModal);

    return (
        <>
        <Button label="Add Recipe to Meal Plan" onClick={toggleOpen} />
        <MDBModal open={basicModal} onClose={() => setBasicModal(false)} tabIndex='-1'>
            <MDBModalDialog>
                <MDBModalContent>
                    <MDBModalHeader>
                        <MDBModalTitle>Select a Meal Plan</MDBModalTitle>
                        <MDBBtn className="btn-close" color='none' onClick={toggleOpen}></MDBBtn>
                    </MDBModalHeader>
                    <MDBModalBody>
                        <p>Need a dropdown menu with a list of meal plans, sorted by most recent</p>
                        <p>And a form that allows the user to create a new meal plan.</p>
                    </MDBModalBody>
                    <MDBModalFooter>
                        <Button label='Close' className="secondaryButton" onClick={toggleOpen} />
                        <MDBBtn>Add to Meal Plan</MDBBtn>
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
        </>
    )


}