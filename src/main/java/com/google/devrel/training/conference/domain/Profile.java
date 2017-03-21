package com.google.devrel.training.conference.domain;
//point
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;
import com.google.devrel.training.conference.form.ProfileForm.TeeShirtSize;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


// TODO indicate that this class is an Entity
@Entity
public class Profile {
    String displayName;
    String mainEmail;
    TeeShirtSize teeShirtSize;

    // TODO indicate that the userId is to be used in the Entity's key
    @Id String userId;

    private List<String> conferenceKeysToAttend = new ArrayList<>(0);
    

    public Profile (String userId, String displayName, String mainEmail, TeeShirtSize teeShirtSize) {
        this.userId = userId;
        this.displayName = displayName;
        this.mainEmail = mainEmail;
        this.teeShirtSize = teeShirtSize;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public TeeShirtSize getTeeShirtSize() {
        return teeShirtSize;
    }

    public String getUserId() {
        return userId;
    }
    

    public List<String> getConferenceKeysToAttend() {
            return ImmutableList.copyOf(conferenceKeysToAttend);
        }
        
        public void addToConferenceKeysToAttend(String conferenceKey) {
            conferenceKeysToAttend.add(conferenceKey);
        }
        
        /**
         * Remove the conferenceId from conferenceIdsToAttend.
         *
         * @param conferenceKey a websafe String representation of the Conference Key.
         */
        public void unregisterFromConference(String conferenceKey) {
            if (conferenceKeysToAttend.contains(conferenceKey)) {
                conferenceKeysToAttend.remove(conferenceKey);
            } else {
                throw new IllegalArgumentException("Invalid conferenceKey: " + conferenceKey);
            }
        }


    private Profile() {}
    

    public void update(String displayName, TeeShirtSize teeShirtSize) {
        if (displayName != null) {
            this.displayName = displayName;
        }
        if (teeShirtSize != null) {
            this.teeShirtSize = teeShirtSize;
        }
    }

}