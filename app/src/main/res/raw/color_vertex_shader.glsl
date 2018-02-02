attribute vec3 position;

varying vec4 color;

uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main()
{
    gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
    color = vec4(0.5, 0.5, 0.5, 1.0);
}
