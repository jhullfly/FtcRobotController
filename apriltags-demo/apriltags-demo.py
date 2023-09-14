#!/usr/bin/env python
import apriltag
import cv2

red = (0, 0, 255) 
green = (0, 255, 0) 
white = (255, 255, 255) 
font = cv2.FONT_HERSHEY_SIMPLEX

cap = cv2.VideoCapture(0)  # 0 is the default camera
at_detector = apriltag.Detector()

target_tag = None

while True:
    # Capture a frame from the camera, convert to greyscale and look for tags.
    ret, frame = cap.read()
    tags = at_detector.detect(cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY))

    if target_tag:
        cv2.putText(frame, f'looking for {target_tag}', (frame.shape[1] - 300, 50), font, 1, white, 2)
    elif tags:
        target_tag = tags[0].tag_id

    # Draw apriltag outlines and print their IDs
    for tag in tags:
        tag_id = tag.tag_id
        color = green if tag_id == target_tag else red
 
        for i in range(4):
            cv2.line(frame, tuple(tag.corners[i-1, :].astype(int)), tuple(tag.corners[i, :].astype(int)), color, 2)
        cv2.putText(frame, f'Tag ID: {tag_id}', (int(tag.corners[3][0])+5, int(tag.corners[3][1])-5), font, 0.5, color, 2)

    cv2.imshow('apriltag demo', frame)

    key = cv2.waitKey(1) & 0xFF
    if key == ord('r'):
        target_tag = None
    elif key == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
