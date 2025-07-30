# scenarios uses
## setup 
- create contact : you need this contact for to test segmentations
- custom object : you need this object for to test segmentations as well

## direct preparation for segments
- CreateSegment : it's both to try the addition functionality but is needed for the next step
- ManageSegment : it shows how conditions are created and it's needed for the segmentation scenario

## complex scenarios
- VerifySegmentAutomation: tests one key feature of the application (it rolls back the changes later)

## independant scenarios
- login success
- login failed
- delete contact
- import contacts: shows how contacts are imported from a csv file (they get deleted after the execution)
- add custom item