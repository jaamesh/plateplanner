import React, { useState } from 'react';
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


export default function App(props) {
    const [basicModal, setBasicModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [tagSaved, setTagSaved] = useState(false);


    const toggleOpen = () => {
        setBasicModal(!basicModal);
        // TODO: If tagSaved is true, refresh the recipe to show the added tags.
        setTagSaved(false);
    }

    const tagRecipe = () => {
        setLoading(true);
        const timeout = setTimeout(() => {
            setLoading(false);
            //setTagSaved(true);
            setError("There was a problem connecting to the database.  Please try again later.");
        }, 3000);
        
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        fetch('http://localhost:8080/search-recipes?q=' + query)
        .then(response => response.json())
        .then(data => { 
            setSearchRecipes(data);
            setLoading(false);
        })
        .catch(error => {
            setError(err.message);
            setLoading(false);
        });
    };

    return (
        <>
            <Button label="Add Tag" onClick={toggleOpen} />
            <MDBModal open={basicModal} onClose={() => setBasicModal(false)} tabIndex='-1'>
                <MDBModalDialog>
                    <MDBModalContent>
                        <MDBModalHeader>
                            <MDBModalTitle>Modal title</MDBModalTitle>
                            <MDBBtn className='btn-close' color='none' onClick={toggleOpen}></MDBBtn>
                        </MDBModalHeader>

                        <MDBModalBody>
                            {!tagSaved && !loading && error == null &&
                                <div>
                                    Add tag to recipe ID: {props.recipeId}<br/>
                                    <label>
                                        Tag to Add:
                                    </label><br/>
                                    Now<br/>
                                    is<br/>
                                    the<br/>
                                    time<br/>
                                    for<br/>
                                    all<br/>
                                    good<br/>
                                </div>
                            }
                            {tagSaved &&
                                <div>Tag saved successfully!</div>
                            }
                            {loading &&
                                <div>Saving tag...</div>
                            }
                            {error != null &&
                                <div>{error}</div>
                            }
                       </MDBModalBody>

                        <MDBModalFooter>
                            <Button label="Close" onClick={toggleOpen} />
                            {!tagSaved && !loading && error == null &&
                                <Button label="Tag Recipe" onClick={tagRecipe}/>
                            }      
                        </MDBModalFooter>
                    </MDBModalContent>
                </MDBModalDialog>
            </MDBModal>
        </>
  );
}