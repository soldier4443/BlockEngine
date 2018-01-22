attribute vec3 position;

varying vec4 color;

uniform mat4 transformationMatrix;

void main()
{
    gl_Position = transformationMatrix * vec4(position, 1.0);
    color = vec4(0.5, 0.5, 0.5, 1.0);
}
